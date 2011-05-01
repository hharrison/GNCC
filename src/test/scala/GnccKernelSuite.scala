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

package GNCC

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

class StackSpec extends WordSpec with ShouldMatchers {
  "Within a network" should {
    val network = new Network
    "All elements should be unique, such as" should {
      "node" in {
        val node = Node(1, 1.0)
        network.add(node) should equal (true)
        network.add(node) should equal (false)
      }
    }
  }
}