/*
 *  This file is part of xor.
 *  Copyright © 2009, Steen Manniche.
 * 
 *  xor is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  xor is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with xor.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.manniche.xor.server;

import java.io.IOException;
import java.net.URI;
import net.manniche.xor.exceptions.RepositoryServiceException;
import net.manniche.xor.storage.StorageProvider;
import net.manniche.xor.types.DefaultIdentifier;
import net.manniche.xor.types.DigitalObject;
import net.manniche.xor.types.ObjectIdentifier;
import net.manniche.xor.types.InternalDigitalObject;
import net.manniche.xor.types.ObjectRepositoryContentType;
import net.manniche.xor.types.CoreRepositoryAction;


/**
 * The RepositoryServer handles the internal and central logic of the
 * repository.
 *
 * The RepositoryServer defines the core operations that the object
 * repository must support. Implementing classes are free to expose
 * additional functionality for clients and even to define additional
 * 'core' operations that other implementations can build upon.
 *
 * Extentions to this class are free (and encouraged) to add
 * functionality such as the ability to index and query the object
 * repository, search-functionality or more advanced operations on
 * objects passing through the repository. Such functionality can be
 * added using the {@link ObjectRepositoryService} interface.
 *
 * @author Steen Manniche
 */
public abstract class RepositoryServer{

    private final StorageProvider repositoryStorageMechanism;

    /**
     * The constructor of the core server should only be used by
     * implementing classes.
     */
    protected RepositoryServer( StorageProvider storage )
    {
        this.repositoryStorageMechanism = storage;
    }

    /**
     * Given data in the form of a byte array, a path to which the
     * object should be stored, and an optional message to the log,
     * this method tries to store the data in the underlying 
     * {@link StorageProvider storage} implementation.
     *
     * @param data the byte array containing data to be stored
     * @param storagePath path to which data will be stored
     * @param message an optional logmessage describing the action. If null or
     * the empty string is passed, the implementation can decide what to write
     * in the log system, if any.
     * @return an ObjectIdentifier that (globally) uniquely identifies the stored
     * data for later identification
     * @throws IOException if the object cannot be stored for a given reason
     */
    protected ObjectIdentifier storeObject( byte[] data, String storagePath, String message ) throws IOException
    {
        return this.storeObject( data, storagePath, null, message);
    }


    /**
     * Identical to {@link net.manniche.xor.server.ObjectRepository#storeObject(byte[], java.lang.String)},
     * except that this method allows the client to pass an identifier along
     * with the object. If the server cannot deliver the exact same identifier
     * as the client provided, when the data is stored, an AssertException should be thrown.
     *
     * @param data the byte array containing data to be stored
     * @param storagePath path to which data will be stored
     * @param identifier the ObjectIdentifier that the object should be stored with
     * @param message an optional logmessage describing the action. If null or
     * the empty string is passed, the implementation can decide what to write
     * in the log system, if any.
     * @return an ObjectIdentifier that is identical to the one delivered by
     * the client
     * @throws IOException if the object cannot be stored for a given reason
     */
    protected ObjectIdentifier storeObject( byte[] data, String storagePath, ObjectIdentifier identifier, String message ) throws IOException
    {
        ObjectIdentifier objectID = null;

        if( null == identifier )
        {
            URI uid = this.repositoryStorageMechanism.save( data, storagePath );
            objectID = new DefaultIdentifier( uid );
        }
        else
        {
            this.repositoryStorageMechanism.save( data, identifier.getURI(), storagePath );
            objectID = identifier;
        }
        return objectID;
    }


    /**
     * Given an ObjectIdentifier this method retrieves the corresponding
     * DigitalObject.
     * @param identifier an ObjectIdentifier identifying the data with this server
     * @return the requested DigitalObject or a IOException if the ObjectIdentifier designates nothing
     * @throws IOException if the DigitalObject can't be retrieved for a given reason
     */
    protected DigitalObject getObject( ObjectIdentifier identifier ) throws IOException
    {
        byte[] object = this.repositoryStorageMechanism.get( identifier.getURI() );
        return new InternalDigitalObject( object );
    }


    /**
     * Given an ObjectIdentifier this method will try to delete a DigitalObject
     * on the server. An IOException is thrown if the operation can't be carried
     * through.
     *
     * @param identifier an ObjectIdentifier identifying the data with this server
     * @param logmessage an optional logmessage describing the action. If null or
     * the empty string is passed, the implementation can decide what to write
     * in the log system, if any.
     * @throws IOException if the DigitalObject couldn't be deleted
     */
    protected void deleteObject( ObjectIdentifier identifier, String logmessage ) throws IOException
    {
        this.repositoryStorageMechanism.delete( identifier.getURI() );
    }

    
    /**
     * Observers who wishes to be notified on repository actions (ie. all the
     * effects of the methods listed in this interface) can register through
     * this method.
     *
     * Is is possible for observers to register more than one time with this
     * implementation. Each registered observer will recieve a separate
     * notification on actions performed that trigger events.
     *
     * @param observer the {@link RepositoryObserver} implementation that
     * wishes to recieve updates
     */
    protected abstract void addObserver( RepositoryObserver observer );

    /**
     * Removes an observer registered with the repository server. If an
     * observer that is not registered is requested removed, this method will
     * silently fail to removed the observer.
     *
     * @param observer the {@link RepositoryObserver} implementation that
     * wishes to be removed from the list of observers
     */
    protected abstract void removeObserver( RepositoryObserver observer );

    /**
     * Notifies all registered observers of the action taken by the
     * {@code RepositoryServer}. This method notifies all and only the observers
     * registered at the point of calling the method. Any observer added during
     * the execution of this method will not be called until the method is
     * called again for a subsequent {@link CoreRepositoryAction}
     *
     * @param identifier the identifier of the object on which the action was performed
     * @param object the object on which the action was performed (can be null, e.g. if the action is 'deleted')
     * @param action the action taken
     * @param contentType the content type of the object
     *
     * @throws RepositoryServiceException if data cannot be retrieved from the {@link DigitalObject}
     *
     * @see CoreRepositoryAction
     * @see ObjectRepositoryContentType
     */
    protected abstract void notifyObservers( ObjectIdentifier identifier, DigitalObject object, CoreRepositoryAction action, ObjectRepositoryContentType contentType ) throws RepositoryServiceException;

}
