package ru.ezhov.pdf

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement

/**
 * Created by rrnezh on 06.12.2017.
 */

@XmlAccessorType(XmlAccessType.NONE)
class DataObject {
    @XmlElement(name = "id")
    int id
    @XmlElement(name = "text")
    String text

    @Override
    String toString() {
        return "id: {" + id + "} text: {" + text + "}"
    }

    boolean equalsRoot(){
        def clearText = text.trim()
        SetRootElements.SET_ROOT_ELEMENTS.contains(clearText) && clearText.split("\\n").length == 1
    }
}
