/*
 *  This file is part of OREP
 *  Copyright © 2009, Steen Manniche.
 * 
 *  OREP is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  OREP is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with OREP.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.manniche.xor.types;


/**
 * An ObjectRepositoryServiceType is the supertype of all objects that can be
 * used as services in the ObjectRepository. All definitions of types must
 * implement this interface. For an example of implementations of this interface
 * see the {@link net.manniche.orep.storage.StorageType} enum. Implementations
 * describe the services that can be handled by the
 * {@link net.manniche.orep.server.ServiceLocator}
 *
 * @see ObjectRepositoryService
 * @author stm
 */
public interface ObjectRepositoryServiceType <T >
{
    public Class<T> getClassofService();
}
