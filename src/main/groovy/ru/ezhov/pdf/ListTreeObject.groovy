package ru.ezhov.pdf

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

/**
 * Created by rrnezh on 06.12.2017.
 */
@XmlRootElement(name = "tree-objects")
@XmlAccessorType(XmlAccessType.NONE)
class ListTreeObject {
    @XmlElement(name = "object")
    private List<TreeObject> objectList = new ArrayList<>()
}
