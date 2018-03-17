def cliBuilder = new CliBuilder(usage: ' -[s] [resource URI] [args to script]')

cliBuilder.with {
    h longOpt: 'help', 'Show usage information'
    s longOpt: 'source', 'Source execute script'
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
    return
}

if (options.s) {
    URI url = new URI(extraArguments[0])

    String[] listArgsForScript = null
    int size = extraArguments.size()
    if (size > 1){
        listArgsForScript = new String[size - 1]

        for (int i = 1; i < size; i++){
            listArgsForScript[i - 1] = extraArguments[i]
        }
    }

    def binding = new Binding(listArgsForScript)
    def gh = new GroovyShell(binding)
    gh.evaluate(url)
}

