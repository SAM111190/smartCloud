import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/background/Layout'
import foreground_layout from "@/layout/foreground/foreground_layout";

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
        {
          path: 'role',
          name: 'Role',
          component: () => import("@/views/background/Role"),
        },
        {
          path: 'menu',
          name: 'Menu',
          component: () => import("@/views/background/Menu"),
        },
        {
          path: 'bulletin',
          name: 'Bulletin',
          component: () => import("@/views/background/Bulletin"),
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
    path: '/forget_password',
    name: 'forget_password',
    component: () => import("@/views/forget_password")
  },
  {
    path: '/front',
    name: 'front_layout',
    component: foreground_layout,
    redirect:"/front/home",
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
      {
        path: 'forum',
        name: 'forum',
        component: () => import("@/views/front/forum/forum")
      },
      {
        path: 'post',
        name: 'post',
        component: () => import("@/views/front/forum/post")
      },
      {
        path: 'post_detail',
        name: 'post_detail',
        component: () => import("@/views/front/forum/post_detail")
      },
      {
        path: 'bulletin',
        name: 'bulletin',
        component: () => import("@/views/front/bulletin board/bulletin")
      },
      {
        path: '404',
        name: '404',
        component: () => import("@/views/front/404/404")
      },
      {
        path: 'search',
        name: 'search',
        component: () => import("@/views/front/search/search")
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
