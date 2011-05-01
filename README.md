# GNCC

Simple demand assignment network calculator.

# Example Syntax/API

    1. Add Nodes
    an 1000
    an 1010
    an 1020
    2. Add Centroids
    ac 1000
    3. Add Links
    al c1000 1010
    al 1010 1020
    4. Add Turns
    at c1000 1010 1020
    5. Delete Operations
    dn 1000
    dc 1000
    dl c1000 1010
    dt c1000 1010 1020

# Installation

* git clone the repo
* sbt compile
* we're not there yet ...

# TODO

# License

GNCC is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
  
GNCC is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
  
You should have received a copy of the GNU General Public License along with GNCC. If not, see http://www.gnu.org/licenses/.