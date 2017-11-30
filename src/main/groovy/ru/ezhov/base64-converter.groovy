import sun.misc.BASE64Decoder

def cliBuilder = new CliBuilder(usage: 'Base64Converter.groovy -[hed] [absolute file path source] [absolute file path target]')

cliBuilder.with {
    h longOpt: 'help', 'Show usage information'
    e longOpt: 'e64', 'Encode Base64: [absolute file path source]'
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
if (options.e) {
    if (extraArguments) {
        String absolutePathToFileSource = extraArguments[0]
        encode(absolutePathToFileSource)
    } else {
        println("Set file source...\nExit")
    }
} else if (options.d) {
    if (extraArguments) {
        String absolutePathToFileSource = extraArguments[0]
        String absolutePathToFileTarget = extraArguments[1]
        decode(
                absolutePathToFileSource,
                absolutePathToFileTarget
        )
    } else {
        println("Set file source and target...\nExit")
    }
}

void encode(String absolutePathToFileSource) {
    def fileSource = new File(absolutePathToFileSource)
    println(fileSource.getBytes().encodeBase64(true).toString())
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
