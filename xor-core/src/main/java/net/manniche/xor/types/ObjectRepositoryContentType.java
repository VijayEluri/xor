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

/**
 * Defines the content types that can be handled by the object repository servers.
 * The basic content type(s) are implemented in
 * {@link net.manniche.xor.types.BasicContentType}
 * @author Steen Manniche
 */
public interface ObjectRepositoryContentType {

    /**
     * @return a String representation of the content type
     */
    @Override
    public String toString();

}
