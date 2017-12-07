package ru.ezhov.pdf

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlElementWrapper

/**
 * Created by rrnezh on 06.12.2017.
 */
@XmlAccessorType(XmlAccessType.NONE)
class TreeObject {
    @XmlElement(name = "name")
    String name

    @XmlElement(name = "description")
    String description

    @XmlElementWrapper(name = "list")
    @XmlElement(name = "object")
    List<TreeObject> childs = new ArrayList()

    void setName(String name) {
        parseNameToNameAndDescription(name)
    }

    private void parseNameToNameAndDescription(String nameDirty) {
        def arrText = nameDirty.trim().split("\\n")
        name = arrText[0]

        def sb = new StringBuilder()
        def size = arrText.size()
        for (int i = 1; i < size; i++) {
            sb.append(arrText[i])
            if (i + 1 < size) {
                sb.append("\n")
            }
        }

        description = sb.toString()

    }

    void setDescription(String description) {
        this.description = description.trim()
    }

    void addChild(TreeObject object) {
        childs.add(object)
    }

    @Override
    String toString() {
        def strB = new StringBuilder()
        strB.append("object [name: ${name}]")
        strB.append("\n")
        childs.each {
            strB.append("\t" + it.toString())
            strB.append("\n")
        }

        return strB.toString()
    }
}
