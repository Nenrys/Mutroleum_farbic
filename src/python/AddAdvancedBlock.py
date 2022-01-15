import pyautogui as pgui
import os
import AddSimpleBlock as ASB


def writetomodblocks(name, classname):
    blockstring1 = '\tpublic static final Block ' + name.upper() + ' = new '+ classname +'('
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


def writeblockclass(class_name):
    blocklines = ['package net.nenrys.mutroleum.blocks;\n\n',
                  'import net.minecraft.block.Block;\n\n',
                  'public class ' + class_name + ' extends Block {\n',
                  '\tpublic ' + class_name + '(Settings settings) {\n',
                  '\t\tsuper(settings);\n',
                  '\t}\n',
                    '}']
    path = os.path.realpath(os.path.join(os.path.dirname(__file__), '..', 'main', 'java', 'net', 'nenrys', 'mutroleum',
                                         'blocks', class_name + '.java'))

    with open(path, 'w') as javafile:
        javafile.writelines(blocklines)




if __name__ == '__main__':
    codename = pgui.prompt("Choose codename:")
    actualname = pgui.prompt("Choose actual name")
    classname = pgui.prompt("Choose class name")

    if codename != '0':
        writetomodblocks(codename, classname)
        ASB.copyblockstates(codename)
        ASB.writetolangfile(codename, actualname)
        ASB.copyblockmodel(codename)
        ASB.copyitemmodel(codename)
        ASB.copyblocktexture(codename)
        ASB.copyblockloottable(codename)
        writeblockclass(classname)
