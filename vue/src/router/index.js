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
        component: () => import("@/views/HomeView"),
      }
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
    component: () => import("@/views/front/Front"),
    children: [
      {
        path: 'home',
        name: 'FrontHome',
        component: () => import("@/views/front/Home")
      },
      {
        path: 'list1',
        name: 'list1',
        component: () => import("@/views/front/list1")
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router