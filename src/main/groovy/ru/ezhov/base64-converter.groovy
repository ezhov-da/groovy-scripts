import sun.misc.BASE64Decoder

def cliBuilder = new CliBuilder(usage: 'Base64Converter.groovy -[hfd] [absolute file path source] [absolute file path target]')

cliBuilder.with {
    h longOpt: 'help', 'Show usage information'
    e longOpt: 'e64', 'Encode Base64: [absolute file path source]'
    f longOpt: 'ef64', 'Encode Base64 to file: [absolute file path source] [absolute file path target]'
    d longOpt: 'd64', 'Decode Base64: [absolute file path source] [absolute file path target]'
}

def options = cliBuilder.parse(args)
if (!options) {
    return
}

if (options.h) {
    cliBuilder.usage()
    return
}

def extraArguments = options.arguments()
if (!extraArguments) {
    println("Use -h for help.")
    println("Exit")
}



if (options.e) {
    encodeE(extraArguments)
} else if (options.f) {
    encodeEF(extraArguments)
} else if (options.d) {
    decodeD(extraArguments)
}

void encodeE(List<String> arguments) {
    String absolutePathToFileSource = arguments[0]
    print(encode(absolutePathToFileSource))
}

void encodeEF(List<String> arguments) {
    String absolutePathToFileSource = arguments[0]
    String absolutePathToFileTarget = arguments[1]

    println("Encode...")
    println("""Source: ${absolutePathToFileSource}""")
    println("""Target: ${absolutePathToFileTarget}""")

    def text = encode(absolutePathToFileSource)
    saveToFile(text, absolutePathToFileTarget)

    println("~Encoding complete~")
}

void decodeD(List<String> arguments) {
    String absolutePathToFileSource = arguments[0]
    String absolutePathToFileTarget = arguments[1]
    decode(
            absolutePathToFileSource,
            absolutePathToFileTarget
    )
}

String encode(String absolutePathToFileSource) {
    def fileSource = new File(absolutePathToFileSource)
    fileSource.getBytes().encodeBase64(true).toString()
}

void saveToFile(String text, String absolutePathToFileTarget) {
    def fileSource = new File(absolutePathToFileTarget)
    fileSource.write(text)
}

void decode(String absolutePathToFileBase64Source, String absolutePathToFileTarget) {
    println("Decode...")
    println("""Source: ${absolutePathToFileBase64Source}""")
    println("""Target: ${absolutePathToFileTarget}""")

    def base64Text = new File(absolutePathToFileBase64Source).getText("UTF-8")
    def base64Decoder = new BASE64Decoder()
    def byteDecode = base64Decoder.decodeBuffer(base64Text)
    new FileOutputStream(absolutePathToFileTarget).write(byteDecode)

    println("~Decoding complete~")
}
