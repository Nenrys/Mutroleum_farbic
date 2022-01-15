import pyautogui as pgui
import shutil
import os


def writetomodblocks(name):
    blockstring1 = '\tpublic static final Block ' + name.upper() + ' = new Block('
    blockstring2 = '\t\t\tFabricBlockSettings.of(Material.STONE).strength(3.0f));'

    registerstring = '\t\tregisterBlockandItem(\"' + name +'\", ' + name.upper() + ');'

    newlinelist = []

    path = os.path.realpath(os.path.join(os.path.dirname(__file__), '..', 'main', 'java', 'net', 'nenrys', 'mutroleum',
                                         'blocks', 'ModBlocks.java'))

    with open(path,'r') as javafile:
        linelist = (line.replace('\n', '') for line in javafile.readlines())
        addblock = False
        registerblock = False

        for line in linelist:
            if addblock:
                newlinelist.append(blockstring1)
                newlinelist.append(blockstring2)
                addblock = False
            if '__0' in line:
                addblock = True

            if registerblock:
                newlinelist.append(registerstring)
                registerblock = False
            if '__1' in line:
                registerblock = True

            newlinelist.append(line)

    with open(path, 'w') as javafile:
        javafile.writelines(line + '\n' for line in newlinelist)


def copyblockstates(name):
    filedir = os.path.realpath(os.path.join(os.path.dirname(__file__),
                                            '..', 'main', 'resources', 'assets', 'mutroleum', 'blockstates'))
    newlines = []
    with open(os.path.join(filedir, 'blockstatetemplate.json'), 'r') as jsonfile:
        filelines = jsonfile.readlines()
        for line in filelines:
            if 'codename' in line:
                line = '    \"\": { "model\": \"mutroleum:block/' + name + '\" }\n'
            newlines.append(line)
    with open(os.path.join(filedir, name + '.json'), 'w') as jsonfile:
        jsonfile.writelines(newlines)


def writetolangfile(cname, aname):
    langstring = '  \"block.mutroleum.' + cname + '\": \"' + aname + '\",'

    path = os.path.realpath(os.path.join(os.path.dirname(__file__),
                                         '..', 'main', 'resources', 'assets', 'mutroleum', 'lang', 'en_us.json'))
    newnamelist = []

    with open(path, 'r') as langfile:
        linelist = (line.replace("\n", '') for line in langfile.readlines())
        addlang = False

        for line in linelist:
            line = line.replace("\n", "")
            if addlang:
                newnamelist.append(langstring)
                addlang = False
            if '__1' in line:
                addlang = True

            newnamelist.append(line)

    with open(path, 'w') as langfile:
        langfile.writelines(line + '\n' for line in newnamelist)


def copyblockmodel(name):
    filedir = os.path.realpath(os.path.join(os.path.dirname(__file__),
                                            '..', 'main', 'resources', 'assets', 'mutroleum', 'models',
                                            'block'))
    newlines = []
    with open(os.path.join(filedir, 'blockmodeltemplate.json'), 'r') as jsonfile:
        filelines = jsonfile.readlines()
        for line in filelines:
            if 'codename' in line:
                line = '    \"all\": \"mutroleum:block/' + name + '\"\n'
            newlines.append(line)
    with open(os.path.join(filedir, name + '.json'), 'w') as jsonfile:
        jsonfile.writelines(newlines)


def copyitemmodel(name):
    filedir = os.path.realpath(os.path.join(os.path.dirname(__file__),
                                            '..', 'main', 'resources', 'assets', 'mutroleum', 'models',
                                            'item'))
    newlines = []
    with open(os.path.join(filedir, 'itemblockmodeltemplate.json'), 'r') as jsonfile:
        filelines = jsonfile.readlines()
        for line in filelines:
            if 'codename' in line:
                line  = '  \"parent\": \"mutroleum:block/' + name + '\"\n'
            newlines.append(line)
    with open(os.path.join(filedir, name + '.json'), 'w') as jsonfile:
        jsonfile.writelines(newlines)


def copyblocktexture(name):
    copyfile = os.path.realpath(os.path.join(os.path.dirname(__file__), '..', 'main', 'resources', 'assets',
                                             'mutroleum', 'textures', 'block', 'TextureTemplate.png'))
    pastefile = os.path.realpath(os.path.join(os.path.dirname(__file__), '..', 'main', 'resources', 'assets',
                                              'mutroleum', 'textures', 'block', name + '.png'))
    shutil.copyfile(copyfile, pastefile)


def copyblockloottable(name):
    filedir = os.path.realpath(os.path.join(os.path.dirname(__file__),
                                            '..', 'main', 'resources', 'data', 'mutroleum', 'loot_tables',
                                            'blocks'))
    newlines = []
    with open(os.path.join(filedir, 'blockloottabletemplate.json'), 'r') as jsonfile:
        filelines = jsonfile.readlines()
        for line in filelines:
            if 'codename' in line:
                line = '    \"layer0\": \"mutroleum:item/' + name + '\"\n'
                line = '          \"name\": \"mutroleum:' + name + '\"\n'
            newlines.append(line)
    with open(os.path.join(filedir, name + '.json'), 'w') as jsonfile:
        jsonfile.writelines(newlines)

if __name__ == '__main__':
    codename = pgui.prompt("Choose codename:")
    actualname = pgui.prompt("Choose actual name")

    if codename != '0':
        writetomodblocks(codename)
        copyblockstates(codename)
        writetolangfile(codename, actualname)
        copyblockmodel(codename)
        copyitemmodel(codename)
        copyblocktexture(codename)
        copyblockloottable(codename)


