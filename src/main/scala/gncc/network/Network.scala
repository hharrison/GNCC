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

object Network {
  def create = new DefaultNetwork
}

trait Network {
  import NetworkOperationStatus._

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
  import NetworkOperationStatus._

  val nodes = new HashSet[Node]
  val links = new HashSet[Link]

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
    val linkNodes = (getNode(i), getNode(j))

    linkNodes match {
      case (Some(Node(i)), Some(Node(j))) => if(links.add(Link(i, j))) Success else LinkExists
      case (None, None) => InvalidNodes
      case (_, None) => JNodeInvalid
      case (None, _) => INodeInvalid
      case _ => LinkExists
    }
  }

  // Currently using this for debugging
  override def toString = {
    "Nodes: " + nodes.toString + "Links: " + links.toString
  }
} 

abstract class NetworkElement
case class Node(id: Int) extends NetworkElement
case class Link(iNode: Int, jNode: Int) extends NetworkElement
case class Turn(iLink: Int, jLink: Int) extends NetworkElement