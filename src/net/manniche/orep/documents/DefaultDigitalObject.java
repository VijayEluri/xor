/*
 *  This file is part of OREP
 *  Copyright © 2009, Steen Manniche.
 * 
 *  RMIObjectRepository is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  RMIObjectRepositoryis distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with RMIObjectRepository.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.manniche.orep.documents;

import net.manniche.orep.types.DigitalObject;


/**
 *
 * @author stm
 */
public class DefaultDigitalObject implements DigitalObject{

    private final byte[] internal_input;

    public DefaultDigitalObject( byte[] input )
    {
        this.internal_input = input;
    }
    @Override
    public byte[] getBytes()
    {

        return this.internal_input;
    }

}
