package ru.ezhov.pdf

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

/**
 * Created by rrnezh on 06.12.2017.
 */
@XmlAccessorType( XmlAccessType.NONE )
@XmlRootElement(name = "data-objects")
class ListDataObject {
    @XmlElement(name = "object")
    List<DataObject> dataObjectList
}
