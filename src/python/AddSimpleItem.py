import pyautogui as pgui
import shutil
import os

def writetomoditems(iname):

    itemstring = "\tpublic static final Item " + iname.upper() + \
                 " = new Item(new FabricItemSettings().group(Mutroleum.MUTROLEUM_GROUP));"
    registerstring = "\t\tregisterItem(\"" + iname + "\", " + iname.upper() + ");"
    newlinelist = []

    path = os.path.realpath(os.path.join(os.path.dirname(__file__), '..', 'main', 'java', 'net', 'nenrys', 'mutroleum',
                                         'items', 'ModItems.java'))
    with open(path, 'r') as javafile:
        linelist = javafile.readlines()
        additem = False
        registeritem = False
        for line in linelist:
            line = line.replace("\n", "")
            if additem:
                newlinelist.append(itemstring)
                additem = False
            if "__0" in line:
                additem = True

            if registeritem:
                newlinelist.append(registerstring)
                registeritem = False
            if "__1" in line:
                registeritem = True

            newlinelist.append(line)

    with open(path, 'w') as javafile:
        javafile.writelines(line + '\n' for line in newlinelist)


def writelang(lname,aname):
    langstring = '  \"item.mutroleum.' + lname + '\": \"' + aname + '\",'

    path = os.path.realpath(os.path.join(os.path.dirname(__file__),
                                         '..', 'main', 'resources', 'assets', 'mutroleum', 'lang', 'en_us.json'))
    newnamelist = []

    with open(path, 'r') as langfile:
        linelist = langfile.readlines()
        addlang = False

        for line in linelist:
            line = line.replace("\n", "")
            if addlang:
                newnamelist.append(langstring)
                addlang = False
            if '__0' in line:
                addlang = True

            newnamelist.append(line)

    with open(path, 'w') as langfile:
        langfile.writelines(line + '\n' for line in newnamelist)


def copymodel(mname):
    filedir = os.path.realpath(os.path.join(os.path.dirname(__file__),
                                            '..', 'main', 'resources', 'assets', 'mutroleum',
                                            'models', 'item', 'templates'))
    newlines = []
    with open(os.path.join(filedir, 'itemmodeltemplate.json'), 'r') as jsonfile:
        filelines = jsonfile.readlines()
        for line in filelines:
            if 'codename' in line:
                line = '\t\"layer0\": \"mutroleum:item/' + mname + '\"\n'
            newlines.append(line)
    with open(os.path.join(filedir, mname + '.json'), 'w') as jsonfile:
        jsonfile.writelines(newlines)


def copytexture(tname):
    copyfile = os.path.realpath(os.path.join(os.path.dirname(__file__), '..', 'main', 'resources', 'assets',
                                             'mutroleum', 'textures', 'item', 'TextureTemplate.png'))
    pastefile = os.path.realpath(os.path.join(os.path.dirname(__file__), '..', 'main', 'resources', 'assets',
                                              'mutroleum', 'textures', 'item', tname + '.png'))
    shutil.copyfile(copyfile, pastefile)

if __name__ == '__main__':
    codename = pgui.prompt("Choose codename:")
    actualname = pgui.prompt("Choose actual name")

    if codename != '0':
        writetomoditems(codename)
        writelang(codename, actualname)
        copymodel(codename)
        copytexture(codename)


