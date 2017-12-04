//test string
//def mapArgs = [separator:".", columns:5, text:"""1.2.3.4.5.6.asgasdg"""]

def mapArgs = ${REPLACE_STRING_DATA}

String separatorVal = mapArgs["separator"]
int columnsVal = mapArgs["columns"] as int
String textVal = mapArgs["text"]


//println(separatorVal)
//println(columnsVal)
//println(textVal)

def String finalString
def StringBuilder stringBuilder
def List<String> listBase
def List<String> listWork
def String[] mass
def Formatter formatter
def int maxChar

stringBuilder = new StringBuilder(300)
formatter = new Formatter()

mass = textVal.tokenize(separatorVal)
mass = mass.collect {
    it.trim()
}
listBase = Arrays.asList(mass)
listWork = new ArrayList(columnsVal)
def maxString = listBase.max { it.size() }
maxChar = maxString.length()

int i = 0; int intWorkMass = 1;
for (int size = listBase.size(); i < size; intWorkMass++) {
    String currentStr = ((String) listBase.get(i)).trim()
    listWork.add(currentStr)
    listBase.set(i, currentStr)
    if (intWorkMass % columnsVal == 0) {
        pasteString(listWork, maxChar, stringBuilder)
        intWorkMass = 0
    }
    i++
}

pasteString(listWork, maxChar, stringBuilder)

def textStringBuilder = stringBuilder.toString()

formatter = formatter.format(stringBuilder.toString(), (Object[]) mass)
formatter.flush()
finalString = formatter.toString()

formatter.close()
maxChar = 0
println " " + finalString.substring(1)

finalString.substring(1)

void pasteString(List<String> listWork, int maxChar, StringBuilder stringBuilder) {
    listWork.each {
        stringBuilder.append(",%-")
        stringBuilder.append(maxChar)
        stringBuilder.append("s")
    }
    stringBuilder.append("\n")
    listWork.clear()
}
