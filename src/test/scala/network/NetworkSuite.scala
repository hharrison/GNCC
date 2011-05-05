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

package gncc.network

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

class NetworkSuiteSpec extends WordSpec with ShouldMatchers {
  import gncc.network.NetworkOperationStatus._

  "A network" should {
    val network = Network.create

    "make it easy to manipulate nodes" that {
      "nodes can easily be added" in {
        network.addNode(1) should be (Success)
        network + 2 should be (Success)
        network an 3 should be (Success)
      }

      "while they produce an error if you add an existing one" in {
        network an 3 should be (NodeExists)
      }
    }

    "then we can manipulate links atop nodes" that {
      "links are easy to add" in {
        network.addLink(1, 2) should be (Success)
        network al (2, 3) should be (Success)
      }

      "but error when we add one that already exists" in {
        network.addLink(1, 2) should be (LinkExists)
      }

      "complain if the j node is invalid" in {
        network.addLink(1,11) should be (JNodeInvalid)
      }

      "complain if the i node is invalid" in {
        network.addLink(11,1) should be (INodeInvalid)
      }

      "complain if both the nodes are invalid" in {
        network.addLink(11,11) should be (InvalidNodes)
      }
    }
  }
}