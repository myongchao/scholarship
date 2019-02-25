import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    meta: { title: '首页', icon: 'dashboard' },
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/teacher',
    component: Layout,
    redirect: '/teacher/teacherManager',
    name: 'teacher',
    meta: { title: '教师用户管理', icon: 'jiaoshi' },
    children: [
      {
        path: 'teacherManager',
        name: 'TeacherManager',
        component: () => import('@/views/teacher/teacherManager'),
        meta: { title: '教师用户管理', icon: 'form' }
      },
      {
        path: 'addTeacher',
        name: 'AddTeacher',
        component: () => import('@/views/teacher/addTeacher'),
        meta: { title: '增加教师用户', icon: 'form' }
      },
      {
        path: 'teacherSelect',
        name: 'TeacherSelect',
        component: () => import('@/views/teacher/teacherSelect'),
        meta: { title: '教师用户查询', icon: 'form' }
      }
    ]
  },

  {
    path: '/classroom',
    component: Layout,
    redirect: '/classroom/classroomManager',
    name: 'classroom',
    meta: { title: '班级信息管理', icon: 'banji' },
    children: [
      {
        path: 'classroomManager',
        name: 'ClassroomManager',
        component: () => import('@/views/classroom/classroomManager'),
        meta: { title: '班级信息管理', icon: 'form' }
      },
      {
        path: 'addClassroom',
        name: 'AddClassroom',
        component: () => import('@/views/classroom/addClassroom'),
        meta: { title: '增加班级信息', icon: 'form' }
      },
      {
        path: 'classroomSelect',
        name: 'ClassroomSelect',
        component: () => import('@/views/classroom/classroomSelect'),
        meta: { title: '班级信息查询', icon: 'form' }
      }
    ]
  },

  {
    path: '/student',
    component: Layout,
    redirect: '/student/studentManager',
    name: 'student',
    meta: { title: '学生信息管理', icon: 'xueyuandangan' },
    children: [
      {
        path: 'studentManager',
        name: 'StudentManager',
        component: () => import('@/views/student/studentManager'),
        meta: { title: '学生信息管理', icon: 'form' }
      },
      {
        path: 'addStudent',
        name: 'AddStudent',
        component: () => import('@/views/student/addStudent'),
        meta: { title: '增加学生信息', icon: 'form' }
      },
      {
        path: 'studentSelect',
        name: 'StudentSelect',
        component: () => import('@/views/student/studentSelect'),
        meta: { title: '学生信息查询', icon: 'form' }
      }
    ]
  },

  // {
  //   path: '/student',
  //   component: Layout,
  //   redirect: '/student',
  //   name: 'student',
  //   meta: { title: '学生信息管理', icon: 'form' },
  //   children: [{
  //     path: 'classroom',
  //     name: 'classroom',
  //     component: () => import('@/views/student/classroom/index'),
  //     meta: { title: '学生信息', icon: 'form' },
  //     children: [
  //       {
  //         path: 'class1',
  //         component: () => import('@/views/student/classroom/class1'),
  //         name: 'class1',
  //         meta: { title: '计科一班' }
  //       },
  //       {
  //         path: 'class2',
  //         component: () => import('@/views/student/classroom/class2'),
  //         name: 'class2',
  //         meta: { title: '计科二班' }
  //       }
  //     ]
  //   },
  //   {
  //     path: 'grade',
  //     name: 'grade',
  //     component: () => import('@/views/student/grade/index'),
  //     meta: { title: '成绩管理', icon: 'form' },
  //     children: [
  //       {
  //         path: 'grade1',
  //         component: () => import('@/views/student/grade/grade1'),
  //         name: 'grade1',
  //         meta: { title: '计科一班' }
  //       },
  //       {
  //         path: 'grade2',
  //         component: () => import('@/views/student/grade/grade2'),
  //         name: 'grade2',
  //         meta: { title: '计科二班' }
  //       }
  //     ]
  //   }
  //   ]
  // },

  {
    path: '/applyIndicators',
    component: Layout,
    redirect: '/applyIndicators',
    name: 'applyIndicators',
    meta: { title: '申请指标管理', icon: 'buke' },
    children: [
      {
        path: 'applyIndicatorManager',
        name: 'ApplyIndicatorManager',
        component: () => import('@/views/applyIndicators/applyIndicatorManager'),
        meta: { title: '申请指标管理', icon: 'table' }
      },
      {
        path: 'addApplyIndicator',
        name: 'AddApplyIndicator',
        component: () => import('@/views/applyIndicators/addApplyIndicator'),
        meta: { title: '添加申请指标', icon: 'table' }
      },
      {
        path: 'applyIndicatorSelect',
        name: 'ApplyIndicatorSelect',
        component: () => import('@/views/applyIndicators/applyIndicatorSelect'),
        meta: { title: '申请指标查询', icon: 'tree' }
      }
    ]
  },

  {
    path: '/application',
    component: Layout,
    redirect: '/application/applyManager',
    name: 'application',
    meta: { title: '申请信息管理', icon: 'yaoqing' },
    children: [
      {
        path: 'applyManager',
        name: 'ApplyManager',
        component: () => import('@/views/application/applyManager'),
        meta: { title: '申请信息管理', icon: 'form' }
      },
      {
        path: 'applySelect',
        name: 'ApplySelect',
        component: () => import('@/views/application/applySelect'),
        meta: { title: '申请信息查询', icon: 'form' }
      },
      {
        path: 'applyCount',
        name: 'ApplyCount',
        component: () => import('@/views/application/applyCount'),
        meta: { title: '申请信息统计', icon: 'tongji' }
      }
    ]
  },

  {
    path: '/personal',
    component: Layout,
    redirect: '/personal',
    name: 'personal',
    meta: { title: '个人信息管理', icon: 'gerenxinxi' },
    children: [{
      path: 'userInfo',
      name: 'userInfo',
      component: () => import('@/views/personal/userInfo'),
      meta: { title: '个人信息', icon: 'form' }
    },
    {
      path: 'resetPwd',
      name: 'resetPwd',
      component: () => import('@/views/personal/resetPwd'),
      meta: { title: '密码管理', icon: 'form' }
    }
    ]
  },

  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'Nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: () => import('@/views/nested/menu1/index'), // Parent router-view
  //       name: 'Menu1',
  //       meta: { title: 'Menu1' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       component: () => import('@/views/nested/menu2/index'),
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },

  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
  //       meta: { title: 'External Link', icon: 'link' }
  //     }
  //   ]
  // },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
