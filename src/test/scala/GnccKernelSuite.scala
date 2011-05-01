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

abstract class NetworkElement
case class Node(id: Int) extends NetworkElement
case class Link(iNode: Int, jNode: Int) extends NetworkElement
case class Turn(iLink: Int, jLink: Int) extends NetworkElement

object Network {
  object NetworkRet extends Enumeration {
    type Reason = Value
    val Success, NodeExists, LinkExists, TurnExists, INodeMissing, JNodeMissing, KNodeMissing, ILinkMissing, JLinkMissing = Value
  }

  def create = new DefaultNetwork
}

trait Network {
  import Network.NetworkRet._

  def getNode(id: Int): Option[Node]

  def an(id: Int): Reason
  def addNode(id: Int) = an(id)
  def +(id: Int) = an(id)

  def al(i: Int, j: Int): Reason
  def addLink(i: Int, j: Int) = al(i, j)
  def +(i: Int, j: Int) = al(i, j)
}

class DefaultNetwork extends Network {
  import scala.collection.mutable.HashSet
  import Network.NetworkRet._

  val nodes = new HashSet[Node]

  def getNode(id: Int) = {
    nodes.findEntry(Node(id))
  }

  def an(id: Int): Reason = {
    if (nodes.add(Node(id)))
      Success
    else
      NodeExists
  }

  def al(i: Int, j: Int): Reason = {
    val nodes = (getNode(i), getNode(j))
    
    nodes match {
      case (iN: Some[Node], jN: Some[Node]) => Success //do link stuff
      case _ => LinkExists
    }
  }
}

class StackSpec extends WordSpec with ShouldMatchers {
  "Create a network" should {
    val network = Network.create
    import Network.NetworkRet._

     "Adding nodes is as simple as" should {
       network.addNode(1) should be (Success)
       network + 2 should be (Success)
       network an 3 should be (Success)
       network an 3 should be (NodeExists)
       network an 1 should be (NodeExists)
     }
     "Adding links is as simple as" should {

       network.addLink(1, 2) should be (Success)
       network.addLink(1, 2) should be (LinkExists)
     }
  }
}

/*
  "Within a network" should {
    val network = new HashMapNetwork
    "All elements should be unique, such as" should {
      val node1a = network addNode 1
      val node1b = network addNode 1
      network addNode 1
      network addNode 3
      network addNode 4
      "node" in {
        node1a should equal (Some(Node(1)))
        node1b should equal (None)
      }
    }
  }
}*/