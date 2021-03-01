import Vue from 'vue'
import ElementUI, { MessageBox } from 'element-ui'
import Message from 'element-ui/packages/message/src/main'

Vue.use(ElementUI)
Vue.prototype.$message = Message
Vue.prototype.$confirm = MessageBox.confirm
