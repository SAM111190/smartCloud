import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout'

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:"/home",
    children:[
      {
        path: 'home',
        name: 'Home',
        component: () => import("@/views/background/HomeView"),
      },
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: () => import("@/views/login")
  },
  {
    path: '/register',
    name: 'register',
    component: () => import("@/views/register")
  },

  {
    path: '/front',
    name: 'Front',
    component: () => import("@/views/front/header/Front_header"),
    children: [
      {
        path: 'user',
        name: 'User',
        component: () => import("@/views/front/user/user")
      },
      {
        path: 'question',
        name: 'Question',
        component: () => import("@/views/front/question/question_doing")
      },
      {
        path: 'home',
        name: 'FrontHome',
        component: () => import("@/views/front/home/Home")
      },
      {
        path: 'person',
        name: '个人空间',
        component: () => import("@/views/front/user/Person.vue")
      },
      {
        path: 'programming',
        name: 'programming',
        component: () => import("@/views/front/Programming")
      },
      {
        path: 'C++',
        name: 'C++',
        component: () => import("@/views/front/list/C++")
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
