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

import java.io.IOException;
import net.manniche.xor.exceptions.RepositoryServiceException;


/**
 * Represents the basic object type in the object repository. Digital objects
 * carry no notion of the type of data carried with the DigitalObject type.
 *
 * DigitalObjects only honour the contract that it must be possible to retrieve
 * the data in the DigitalObject as a byte[]
 * 
 * @author Steen Manniche
 */
public interface DigitalObject {

    /**
     * Writes out the DigitalObject as a byte[]. The binary representation is
     * determined by the implementors.
     *
     * @throws IOException if the DigitalObject could not be converted to a byte[]
     * @return the object as a byte array
     */
    public byte[] getBytes() throws RepositoryServiceException;

}
