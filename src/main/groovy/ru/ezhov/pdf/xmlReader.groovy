package ru.ezhov.pdf

import javax.xml.bind.JAXB

ListDataObject listDataObject = JAXB.unmarshal(new File("output.xml"), ListDataObject)
//println(listDataObject.dataObjectList)

def listTreeObject = new ArrayList<>()
def rootTreeObject = new TreeObject(name: "root")
listTreeObject.add(rootTreeObject)
def lastTreeObject = null
def ldo = listDataObject.dataObjectList
ldo.each {
    def to = new TreeObject()
    to.setName(it.text)
    if (it.equalsRoot()) {
        rootTreeObject.childs.add(to)
        lastTreeObject = to
    } else {
        lastTreeObject.addChild(to)
    }
}

println(listTreeObject)

ListTreeObject listTreeObjectO = new ListTreeObject()
listTreeObjectO.objectList = listTreeObject

JAXB.marshal(listTreeObjectO, new File("tree.xml"))
