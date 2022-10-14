import btt_portal.Logging
import internal.GlobalVariable

Logging gv = new Logging()
gv.addGlobalVariable('test', 'hello world')

println GlobalVariable.test