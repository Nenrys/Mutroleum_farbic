with open("texttest2.java", 'w') as testfile:

    simplejava = ['public class TestPythoncalss {',
                  '\t//random comment',
                  '\tprivate static final int TESTING = 0;',
                  '}']
    testfile.writelines(line + '\n' for line in simplejava)

