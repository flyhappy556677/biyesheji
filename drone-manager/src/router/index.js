import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/Manage.vue'
import store from "@/store";

Vue.use(VueRouter)

const routes = [

  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
    },
    {
      path: '/register',
      name: 'Register',
      component: () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
    },
    {
      path: '/404',
      name: '404',
      component: () => import(/* webpackChunkName: "about" */ '../views/404.vue')
    },
    {
      path: '/front',
      name: 'Front',
      component: () => import(/* webpackChunkName: "about" */ '../views/front/Front.vue'),
      children:[
          {
            path: 'home',
            name: 'FrontHome',
            component: () => import(/* webpackChunkName: "about" */ '../views/front/Home.vue')
          },
          {
            path: 'item',
            name: 'item',
            component: () => import(/* webpackChunkName: "about" */ '../views/front/item.vue')
          },
      ]
    },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//提供一个重置路由的方法
export const resetRouter=() =>{
  router.matcher =new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
  })
}
//刷新页面会重置路由
export  const setRoutes=() =>{
  const storeMenus=localStorage.getItem("menus");
  if(storeMenus){
    //获取当前的路由对象名称数组
    const currentRouteNames =router.getRoutes().map(v =>v.name)
    if(!currentRouteNames.includes('Manage')){
      //拼装动态路由
      const manageRoute=
          {path: '',name:'Manage', component: () => import( '../views/Manage.vue'), redirect:"/home",children:[
          {path: 'person', name: '个人信息', component: () => import(/* webpackChunkName: "about" */ '../views/Person.vue')}]}
      const menus=JSON.parse(storeMenus)
      menus.forEach(item =>{
        if(item.path){//当且仅当path不为空的时候才去设置路由
          let itemMenu={path: item.path.replace("/",""),name:item.name,component:() => import('../views/'+item.pagePath +'.vue')}
          manageRoute.children.push(itemMenu)
        }else if (item.children.length){
          item.children.forEach(item =>{
            if(item.path){
              let itemMenu={path: item.path.replace("/",""),name:item.name,component:() => import('../views/'+item.pagePath +'.vue')}
              manageRoute.children.push(itemMenu)}
          })
        }

      })
      // 根据数据库中的信息动态生成详情页面的路由
      // 假设数据库中存储了详情页面的信息，包括路径等
      const detailPage = {
        path: 'detail',
        name: '详情页面',
        component: () => import('../views/Detail.vue') // 假设详情页面的路径是 'Detail.vue'
      };
      // 根据数据库中的信息动态生成详情页面的路由
      // 假设数据库中存储了详情页面的信息，包括路径等
      const OperatedronePage = {
        path: 'operatedrone',
        name: '详情页面',
        component: () => import('../views/Operatedrone.vue') // 假设详情页面的路径是 'Detail.vue'
      };
      const AddMaintenance = {
        path: 'AddMaintenance',
        name: '保养清单',
        component: () => import('../views/AddMaintenance.vue') // 假设详情页面的路径是 'Detail.vue'
      };
      const MATman = {
        path: 'MATman',
        name: '保养清单',
        component: () => import('../views/MATman.vue') // 假设详情页面的路径是 'Detail.vue'
      };
      manageRoute.children.push(detailPage);
      manageRoute.children.push(OperatedronePage);
      manageRoute.children.push(AddMaintenance);
      manageRoute.children.push(MATman);
      //动态添加到现在的路由对象中
      router.addRoute(manageRoute)
    }
  }
}
//动态添加到现在的路由对象中
setRoutes()

router.beforeEach((to, from, next) =>{
  localStorage.setItem("currentPathName",to.name) //设置当前路由名称,为了在Header组件使用
  store.commit("setPath") //触发store的数据更新

  //未找到路由情况
  if (!to.matched.length){
    const storeMenus=localStorage.getItem("menus")
    if(storeMenus){
      next("/404") //放行路由
    }else {
      next("/login")
    }
  }
  //其他的情况都放行
  next()


})

export default router
