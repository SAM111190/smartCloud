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
      },
      {
        path: 'person',
        name: '个人空间',
        component: () => import("@/views/Person.vue")
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
    component: () => import("@/views/front/Front"),
    children: [
      {
        path: 'user',
        name: 'User',
        component: () => import("@/views/front/user")
      },
      {
        path: 'question',
        name: 'Question',
        component: () => import("@/views/front/question_doing")
      },
      {
        path: 'home',
        name: 'FrontHome',
        component: () => import("@/views/front/Home")
      },
      {
        path: 'tab',
        name: 'Time and back',
        component: () => import("@/views/front/tab"),
        children:[
          {
            path: 'programming',
            name: 'programming',
            component: () => import("@/views/front/Programming")
          },
          {
            path: 'C++',
            name: 'C++',
            component: () => import("@/views/front/C++")
          },

        ]
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
