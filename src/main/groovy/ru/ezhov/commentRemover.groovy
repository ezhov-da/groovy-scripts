def pathToFolderForReplace = """E:/programmer/git/dictionary-desktop-app"""

def rootFolder = new File(pathToFolderForReplace)

rootFolder.eachFileRecurse {
    if (it.file && it.name.endsWith(".java")) {
        removeCommentFromFile(it)
    }
}

void removeCommentFromFile(File file) {
    def text = file.getText("UTF-8")
    def textAfterReplace = text.replaceAll("\\/\\*\\s+?(\\d+:)\\s?(\\d+)?\\s+\\*\\/", "")
    println(textAfterReplace)

    //ПЕРЕЗАПИСЬ ФАЙЛА============================
    file.write(textAfterReplace, "UTF-8")
    //============================================
}