package ru.ezhov.pdf

/**
 * Created by rrnezh on 06.12.2017.
 */
class ObjectText {
    int id //for ignore with create
    String text

    @Override
    String toString() {
        return "id: {" + id + "} text: {" + text + "}"
    }
}
