/*
 *  This file is part of xor
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

package net.manniche.xor.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Default implementation of the {@link net.manniche.xor.types.DigitalObject}
 * This type only supports the most basic operations, including and limited to
 * storing the data contents and defining the
 * {@link net.manniche.xor.types.ObjectRepositoryContentType content type} of
 * the data.
 *
 * The DefaultDigitalObject is an immutable type and thereby completely thread-
 * safe.
 * 
 */
@XmlRootElement
public final class RESTDigitalObject implements DigitalObject, Serializable{
    static final long serialVersionUID = 4558861702889722277L;

    private byte[] internal_input;
    private ObjectRepositoryContentType contentType;

    
    public void setBytes( byte[] input )
    {
        this.internal_input = input;
    }
    
    public void setContentType( ObjectRepositoryContentType contentType )
    {
        this.contentType = contentType;
    }
    
    
    @Override
    public byte[] getBytes()
    {
        return this.internal_input;
    }

    /**
     * Returns the {@link net.manniche.xor.types.ObjectRepositoryContentType}
     * defined by the server implementor.
     *
     * @return the content type of the DigitalObject
     */
    public ObjectRepositoryContentType getContentType()
    {
        return this.contentType;
    }
}
