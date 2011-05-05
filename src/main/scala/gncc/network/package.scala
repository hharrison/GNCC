/**
 * Copyright 2011 Harvey Harrison & Saem Ghani
 * 
 * This file is part of GNCC.
 * 
 * GNCC is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * GNCC is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty 
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with GNCC. If not, see 
 * http://www.gnu.org/licenses/.
 */

package gncc

package object network {
  object NetworkOperationStatus extends Enumeration {
    type Reason = Value
    val Success, NodeExists, LinkExists, TurnExists, InvalidNodes, INodeInvalid, JNodeInvalid, KNodeInvalid, ILinkInvalid, JLinkInvalid = Value
  }
}

 
