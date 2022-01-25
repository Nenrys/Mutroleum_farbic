import pyautogui as pgui
import os
import AddSimpleItem as ASI

def writetomoditems(iname, classname):

    itemstring = "\tpublic static final Item " + iname.upper() + \
                 " = new " + classname + "(new FabricItemSettings().group(Mutroleum.MUTROLEUM_GROUP));"
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


def writeitemclass(class_name):
    itemlines = ['package net.nenrys.mutroleum.items;\n\n',
                  'import net.minecraft.item.Item;\n\n',
                  'public class ' + class_name + ' extends Item {\n',
                  '\tpublic ' + class_name + '(Settings settings) {\n',
                  '\t\tsuper(settings);\n',
                  '\t}\n',
                  '}']
    path = os.path.realpath(os.path.join(os.path.dirname(__file__), '..', 'main', 'java', 'net', 'nenrys', 'mutroleum',
                                         'items', class_name + '.java'))

    with open(path, 'w') as javafile:
        javafile.writelines(itemlines)




if __name__ == '__main__':
    codename = pgui.prompt("Choose codename:")
    actualname = pgui.prompt("Choose actual name")
    classname = pgui.prompt("Choose class name")

    if codename != '0':
        writetomoditems(codename, classname)
        ASI.writelang(codename, actualname)
        ASI.copymodel(codename)
        ASI.copytexture(codename)

        writeitemclass(classname)