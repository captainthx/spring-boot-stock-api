## ADMIN WEB
## List
>สารบัญ
- [ADMIN WEB](#admin-web)
  - [Technology](#technology)
    - [Program](#program)
    - [Programing Language](#programing-language)
    - [Framework](#framework)
    - [Other](#other)
  - [Project Structure](#project-structure)
    - [Api](#api)
    - [I18n](#i18n)
    - [Layout](#layout)
    - [Menu](#menu)
    - [Router](#router)
    - [Store](#store)
    - [View](#view)
    - [Main](#main)
    - [App](#app)
    - [Vite](#vite)
- [Script Npm](#script)
    - [Start Project](#start-project)
    - [How to run](#how-to-run)
    - [How to build](#how-to-build)
    - [How to build test](#how-to-build-test)
    - [How to build production](#how-to-build-production)
    - [How to type check](#how-to-type-check)
    - [How to lint](#how-to-lint-use-eslint)
    - [How to format](#how-to-format-code)
    - [How to lint type check format code](#how-to-lint-type-check-format-code)
  

## Technology

### Program

- vscode
    - code editor

### Programing Language

- typescript
    - ภาษาที่ใช้ในการเขียนโปรแกรม

### Framework
- vue ts (version 3)

### Other
- node js
  - ภาษาที่ใช้ในการรันโปรแกรม
- axios
  - ไลบรารี่ที่ใช้ในการเชื่อมต่อกับ api
- dayjs
  - ไลบรารี่ที่ใช้ในการจัดการเวลา
- vue-router
  - ไลบรารี่ที่ใช้ในการจัดการ route
- vue-i18n
  - ไลบรารี่ที่ใช้ในการจัดการภาษา
- ant-design-vue
  - ไลบรารี่ที่ใช้ในการจัดการ ui

## Project Structure
- src
    - assets
        - รูปภาพที่ใช้ในโปรแกรม
    - components
        - โค้ดที่ใช้ในการสร้าง component
    - router
        - โค้ดที่ใช้ในการกำหนด route
    - store
        - โค้ดที่ใช้ในการจัดการ state
    - i18n
        - โค้ดที่ใช้ในการกำหนดภาษา
    - service
        - โค้ดที่ใช้ในการเชื่อมต่อกับ api
    - views
        - โค้ดที่ใช้ในการสร้างหน้าเว็บ
    - App.vue
        - ไฟล์หลักของโปรแกรม
    - main.ts
        - ไฟล์ที่ใช้ใน
    - git ignore
        - ไฟล์ที่ใช้ในการกำหนดไฟล์ที่ไม่ต้องการให้ git จดจำ
    - package.json
        - ไฟล์ที่ใช้ในการกำหนด package ที่ต้องการใช้ในโปรแกรม
    - tsconfig.json
        - ไฟล์ที่ใช้ในการกำหนดค่า typescript
    - env
        - ไฟล์ที่ใช้ในการกำหนดค่าต่างๆ
    - eslint
        - ไฟล์ที่ใช้ในการกำหนดค่า eslint
    - prettier
        - ไฟล์ที่ใช้ในการกำหนดค่า prettier

### Api
> การเชื่อมต่อกับ api จะอยู่ในโฟลเดอร์ api
- index.d.ts
    - ไฟล์ที่ใช้ในการกำหนด type ของ api
```typescript
export interface ApiResponse<T> {
    /** code */
    code: number;
    /** description */
    description: string;
    /** result */
    result?: T;
    /** Data size of list data */
    records: number;
}


export type ServerResponse<T> = Promise<AxiosResponse<ApiResponse<T>>>;

/**
 * Api pagination
 */
export interface Pagination {
    /** page number */
    page?: number;
    /** limit */
    limit?: number;
    sorts?: string;
}

/**
 * Token response
 */
export interface TokenResponse {
    /** token */
    token: string;
    /** refresh token */
    refresh: string;
    /** expoire token*/
    expire: number;
    /** expire refresh token */
    refreshExpire: number;
}

```
- client.ts
    - ไฟล์ กำหนดค่า axios
```typescript
import axios from 'axios';

const create = function () {
    return axios.create({
        baseURL: import.meta.env.VITE_API_BASEURL
    });
};

const client = create();

export {client as default, create};
```
- auth.ts
    - ไฟล์ ที่ใช้ในการเชื่อมต่อกับ api ที่เกี่ยวกับ auth
```typescript
import type {ServerResponse, TokenResponse, LoginBody, RefreshBody} from '.';

import {create} from './request';

const authClient = create();

const login = (body: LoginBody): ServerResponse<TokenResponse> =>
    authClient.post('/v1/auth/login', body);
const refresh = (body: RefreshBody): ServerResponse<TokenResponse> =>
    authClient.post('/v1/auth/refresh', body);

export {login, refresh};
```
- Enum
    - ไฟล์ที่ใช้ในการกำหนด enum ต่างๆ
```typescript
/**
 * response code
 */
export enum ResponseCode {
    /** success */
    SUCCESS = 0,
    /** token expires*/
    TOKEN_EXPIRE = 4001,
    /** refresh token */
    REFRESH_TOKEN_EXPIRE = 4002
}

/**
 * constant
 */
export enum PaginationConstant {
    /** Default page number */
    DEFAULT_PAGE = 1,
    /** Default number of pages */
    DEFAULT_LIMIT = 10
}
```
### I18n
> โฟลเดอร์ที่เก็บไฟล์กำหนดภาษา
- en.us.ts
    - ไฟล์ที่ใช้ในการกำหนดภาษา en.us
```typescript
const messages = {
    code: 'en-US' as string,
    locale: 'enUS',
    language: 'English',
    menu: {
        home: 'home',
        wallet: 'wallet',
        carrier: 'carrier',
        member: {
            name: 'member',
            list: 'member list',
            invitationCode: 'invitation code',
            signUp: 'sign up',
            identity: 'identity list',
            agentLink: 'agent link',
            performance: 'performance',
            activeAudit: 'active audit'
        }
    }
};
export default messages;
```
### Layout
> โฟลเดอร์ที่เก็บไฟล์ layout
- Header
    - ไฟล์ที่ใช้ในการกำหนด header
```vue

<template>
  <a-modal
      v-model:visible="state.logout"
      :title="$t('title.confirm')"
      @ok="handleClickOkLogout"
      @cancel="handleClickCancelLogout"
      :okButtonProps="logoutButtonProps"
  >
    <p>{{ $t('ask.logout') }}</p>
  </a-modal>
  <a-modal v-model:visible="state.changePassword" :title="$t('action.changePassword')">
    <a-form ref="form" :model="form" :rules="rules" :labelCol="constant.defaultLabelCol">
      <a-form-item :label="$t('label.currentPassword')" name="current">
        <a-input-password v-model:value="form.current"/>
      </a-form-item>
      <a-form-item :label="$t('label.newPassword')" name="new">
        <a-input-password v-model:value="form.new"/>
      </a-form-item>
      <a-form-item v-if="state.error" :wrapperCol="constant.defaultOffset">
        <p class="error-text">{{ $t(`responseCode.${state.error}`) }}</p>
      </a-form-item>
    </a-form>
    <template #footer>
      <a-button type="primary" :loading="state.submitting" @click="handleChangePassword">{{
        $t('action.changePassword')
        }}
      </a-button>
    </template
    >
  </a-modal
  >
  <a-menu
      mode="horizontal"
      theme="dark"
      class="head-menu"
      v-model:selectedKeys="state.logoutSelectedKeys"
  >
    <a-sub-menu key="1000">
      <template #icon>
        <user-outlined/>
      </template>
      <a-menu-item key="1" @click="handleClickChangePassword">
        <template #icon>
          <setting-outlined/>
        </template>
        {{ $t('action.changePassword') }}
      </a-menu-item>
      <a-menu-item key="2" @click="handleClickLogout">
        <template #icon>
          <logout-outlined/>
        </template>
        {{ $t('action.logout') }}
      </a-menu-item
      >
    </a-sub-menu>
    <a-sub-menu key="1100" v-if="languages.length > 1">
      <template #icon>
        <global-outlined/>
      </template>
      <template v-for="item in languages" :key="item.code">
        <a-menu-item @click="handleClickLanguage(item.code)">{{ item.language }}</a-menu-item>
      </template>
    </a-sub-menu>
  </a-menu>
</template>
<script lang="ts">
  import {
    GlobalOutlined,
    UserOutlined,
    LogoutOutlined,
    SettingOutlined
  } from '@ant-design/icons-vue';
  import {mapStores} from 'pinia';
  import {useAuthStore} from '@/stores/auth';
  import {managerChangePassword} from '@/api/manager';
  import {message} from 'ant-design-vue';

  export default {
    components: {
      GlobalOutlined,
      UserOutlined,
      LogoutOutlined,
      SettingOutlined
    },
    data() {
      return {
        state: {
          logout: false as Boolean,
          logoutting: false as Boolean,
          logoutSelectedKeys: [] as String[],
          changePassword: false as Boolean,
          submitting: false as Boolean,
          error: undefined as string | undefined
        },
        constant: {
          defaultLabelCol: {
            span: 8
          },
          defaultOffset: {
            offset: 16
          },
          defaultRows: 15 as number
        },
        form: {
          current: undefined as string | undefined,
          new: undefined as string | undefined
        },
        rules: {
          current: [
            {
              required: true,
              whitespace: true,
              message: this.$t('tips.inputCurrentPassword'),
              trigger: 'blur'
            },
            {
              type: 'string',
              message: this.$t('tips.inputCurrentPassword'),
              trigger: 'blur'
            },
            {
              min: 4,
              max: 32,
              message: this.$t('tips.inputCurrentPassword'),
              trigger: 'blur'
            }
          ],
          new: [
            {
              required: true,
              whitespace: true,
              message: this.$t('tips.inputNewPassword'),
              trigger: 'blur'
            },
            {
              type: 'string',
              message: this.$t('tips.inputNewPassword'),
              trigger: 'blur'
            },
            {
              min: 4,
              max: 32,
              message: this.$t('tips.inputNewPassword'),
              trigger: 'blur'
            }
          ]
        }
      };
    },
    mounted() {
      this.state.logoutSelectedKeys.push(this.$i18n.locale);
    },
    computed: {
      ...mapStores(useAuthStore),
      languages() {
        const entities = [];
        for (const message of Object.values(this.$i18n.messages)) {
          entities.push({code: message.code, language: message.language});
        }
        return entities;
      },
      logoutButtonProps() {
        return {
          loading: this.state.logoutting
        };
      }
    },
    methods: {
      handleClickLogout() {
        this.state.logout = true;
      },
      handleClickChangePassword() {
        this.state.changePassword = true;
      },
      handleClickOkLogout() {
        this.state.logoutting = true;
        this.authStore.logout();
        $router.push({name: 'login'});
      },
      handleClickCancelLogout() {
        const index = this.state.logoutSelectedKeys.indexOf('1000');
        if (index >= 0) {
          this.state.logoutSelectedKeys.splice(index, 1);
        }
      },
      handleClickLanguage(code: string) {
        if (code === this.$i18n.locale) {
          return;
        }
        const index = this.state.logoutSelectedKeys.indexOf(code);
        if (index >= 0) {
          this.state.logoutSelectedKeys.splice(index, 1);
        }
        this.$i18n.locale = code;
        this.state.logoutSelectedKeys.push(code);
      },
      async handleChangePassword() {
        try {
          const response = await managerChangePassword({
            cur: this.form.current,
            latest: this.form.new
          });
          if (response.status === 200 && response.data.code === 0) {
            message.success(this.$t('message.action.success'));
            this.form.current = undefined;
            this.form.new = undefined;
            this.state.error = undefined;
          }
          if (response.status === 200 && response.data.code !== 0) {
            this.state.error = response.data.code;
          }
        } catch (error) {
          console.error(error);
          this.state.error = error.code;
        }
      }
    }
  };
</script>
<style lang="scss">
  .head-menu {
    flex-direction: row-reverse;
  }
</style>
```
- SideMenu
  - ไฟล์ที่ใช้ในการกำหนด sideMenu
```vue
<template>
  <template v-for="menu in treeData" :key="menu.id">
    <template v-if="needMenu(menu.type)">
      <template v-if="isFolder(menu.type)">
        <a-sub-menu :key="menu.id">
          <template #icon>
            <folder-outlined />
          </template>
          <template #title>{{ menu.i18n ? $t(menu.i18n) : menu.name }}</template>
          <c-menu v-if="menu.children" :treeData="menu.children"></c-menu>
        </a-sub-menu>
      </template>
      <template v-if="isMenu(menu.type)">
        <a-menu-item :key="menu.id">
          <template #icon>
            <bars-outlined />
          </template>
          <router-link :to="{ name: menu.url }">{{
            menu.i18n ? $t(menu.i18n) : menu.name
          }}</router-link>
        </a-menu-item>
      </template>
    </template>
  </template>
</template>
<script lang="ts">
import type { ProfileMenus } from '@/stores';
import { MenuType } from '@/api/enum';
import { FolderOutlined, BarsOutlined } from '@ant-design/icons-vue';

export default {
  name: 'CMenu',
  components: {
    FolderOutlined,
    BarsOutlined
  },
  props: {
    treeData: {
      type: Array as () => ProfileMenus[],
      required: true
    }
  },
  methods: {
    needMenu(type: number): boolean {
      return type === MenuType.MENU || type === MenuType.FOLDER;
    },
    isFolder(type: number): boolean {
      return type === MenuType.FOLDER;
    },
    isMenu(type: number): boolean {
      return type === MenuType.MENU;
    }
  }
};
</script>
```
- Layout
    - ไฟล์ที่ใช้ในการกำหนด layout
```vue
<template>
  <a-layout class="layout">
    <a-layout-sider class="sider" v-model:collapsed="state.collapsed" collapsible>
      <div class="head"></div>
      <SideMenu></SideMenu>
      <div class="bottom-menu">&nbsp;</div>
    </a-layout-sider>
    <div class="virtual-sider"></div>
    <a-layout>
      <a-layout-header class="header">
        <CHeader></CHeader>
      </a-layout-header>
      <div class="virtual-header"></div>
      <a-layout-content class="content">
        <RouterView />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script lang="ts">
import SideMenu from './CSideMenu.vue';
import CHeader from './CHeader.vue';

export default {
  components: {
    SideMenu,
    CHeader
  },
  data() {
    return {
      state: {
        collapsed: false
      }
    };
  },
  computed: {
    sideWidth() {
      return this.state.collapsed ? '80px' : '200px';
    }
  }
};
</script>
<style lang="scss" scoped>
.layout {
  $header-height: 64px;
  $sider-height: v-bind(sideWidth);

  min-height: 100vh;

  .sider {
    position: fixed;
    height: 100%;
    z-index: 1;
    overflow-y: scroll;

    .head {
      height: $header-height;
    }

    .bottom-menu {
      height: 48px;
    }
  }

  .virtual-sider {
    width: $sider-height;
  }

  .header {
    position: fixed;
    width: calc(100% - $sider-height);
    z-index: 999;
  }

  .virtual-header {
    height: $header-height;
  }

  .content {
    margin: {
      top: 16px;
      left: 16px;
      right: 16px;
    }
  }
}
</style>
```
- Menu
    - ไฟล์ที่ใช้ในการกำหนด Menu
```vue
<template>
  <template v-for="menu in treeData" :key="menu.id">
    <template v-if="needMenu(menu.type)">
      <template v-if="isFolder(menu.type)">
        <a-sub-menu :key="menu.id">
          <template #icon>
            <folder-outlined />
          </template>
          <template #title>{{ menu.i18n ? $t(menu.i18n) : menu.name }}</template>
          <c-menu v-if="menu.children" :treeData="menu.children"></c-menu>
        </a-sub-menu>
      </template>
      <template v-if="isMenu(menu.type)">
        <a-menu-item :key="menu.id">
          <template #icon>
            <bars-outlined />
          </template>
          <router-link :to="{ name: menu.url }">{{
            menu.i18n ? $t(menu.i18n) : menu.name
          }}</router-link>
        </a-menu-item>
      </template>
    </template>
  </template>
</template>
<script lang="ts">
import type { ProfileMenus } from '@/stores';
import { MenuType } from '@/api/enum';
import { FolderOutlined, BarsOutlined } from '@ant-design/icons-vue';

export default {
  name: 'CMenu',
  components: {
    FolderOutlined,
    BarsOutlined
  },
  props: {
    treeData: {
      type: Array as () => ProfileMenus[],
      required: true
    }
  },
  methods: {
    needMenu(type: number): boolean {
      return type === MenuType.MENU || type === MenuType.FOLDER;
    },
    isFolder(type: number): boolean {
      return type === MenuType.FOLDER;
    },
    isMenu(type: number): boolean {
      return type === MenuType.MENU;
    }
  }
};
</script>
```
### Router
> โฟลเดอร์ที่เก็บไฟล์ตั้งค่า router
```typescript
import type { RouteRecordRaw } from 'vue-router';
import type { AxiosResponse } from 'axios';
import type { ProfileMenus } from '@/stores';
import type { ApiResponse } from '@/api';
import { ResponseCode, MenuType } from '@/api/enum';

import { createRouter, createWebHistory } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
import { usePermissionStore } from '@/stores/permission';
import client from '@/api/request';
const modules = import.meta.glob(['@/views/*/*.vue', '@/views/*/*/*.vue']);

const routes = [
  {
    // login
    path: '/login',
    name: 'login',
    component: () => import('@/views/CLogin.vue')
  },
  {
    // layout
    path: '/',
    name: 'layout',
    component: () => import('@/layout/CLayout.vue')
  }
] as RouteRecordRaw[];

let isAddDynamicMenuRoutes = false;
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

const depthAddRoute = async (menus: ProfileMenus[], parent: ProfileMenus | undefined) => {
  if (menus.length === 0) {
    return;
  }
  for (const menu of menus) {
    if (menu.type === MenuType.MENU && menu.url) {
      router.addRoute('layout', {
        path: menu.url,
        name: menu.url,
        component: modules[`/src/views/${menu.path}.vue`],
        meta: {
          id: menu.id,
          parent: parent ? parent.id : undefined
        }
      });
    }
    if (menu.children) {
      await depthAddRoute(menu.children, menu);
    }
  }
};

client.interceptors.request.use(async (config) => {
  const authStore = useAuthStore();
  if (authStore.expired) {
    if (authStore.refreshExpired) {
      router.push({ name: 'login' });
      return Promise.reject('refresh');
    }
    await authStore.refreshAuth();
  }
  // Authorization，
  config.headers.Authorization = authStore.token;
  return config;
});

client.interceptors.response.use(async (response: AxiosResponse<ApiResponse<any>, any>) => {
  if (response.data && response.data.code === ResponseCode.TOKEN_EXPIRE) {
    const authStore = useAuthStore();
    try {
      const refreshResponse = await authStore.refreshAuth();
      if (refreshResponse.data) {
        if (refreshResponse.data.code === ResponseCode.SUCCESS) {
          return await client(response.config);
        }
      }
    } catch (error) {
      console.error('error:', error);
      if (error.code && error.code === ResponseCode.REFRESH_TOKEN_EXPIRE) {
        router.push({ name: 'login' });
      }
    }
  }
  return response;
});

router.beforeEach(async (to) => {
  if (to.name === 'login') {
    return true;
  }
  if (isAddDynamicMenuRoutes === true) {
    return true;
  }
  const permissionStore = usePermissionStore();
  await permissionStore.load();
  await depthAddRoute(permissionStore.treeMenus, undefined);
  isAddDynamicMenuRoutes = true;
  return { path: to.path };
});

export default router;
```
### Store
> โฟลเดอร์ที่เก็บไฟล์ตั้งค่า store
- auth
    - ไฟล์ที่ใช้ในการกำหนด store ของ auth
```typescript
import type { AxiosResponse } from 'axios';
import type { AuthState } from '.';
import type { ApiResponse, TokenResponse, LoginBody, RefreshBody } from '@/api';

import { defineStore } from 'pinia';
import { login, refresh } from '@/api/auth';

const storage = {
  set(token: string, refresh: string) {
    window.sessionStorage.setItem('token', token);
    window.sessionStorage.setItem('refresh', refresh);
  }
};

export const useAuthStore = defineStore('auth', {
  state: (): AuthState => {
    const token = window.sessionStorage.getItem('token');
    const refresh = window.sessionStorage.getItem('refresh');
    const state = {
      token,
      refresh
    } as AuthState;
    if (token) {
      state.payload = JSON.parse(atob(token.split('.')[1]));
    }
    if (refresh) {
      state.refreshPayload = JSON.parse(atob(refresh.split('.')[1]));
    }
    return state;
  },
  getters: {
    // is active
    isActive(): boolean {
      return this.expired === false;
    },
    // token
    expired(): boolean {
      return this.payload && this.payload.exp ? Date.now() / 1000 > this.payload.exp : true;
    },
    // refresh token expired
    refreshExpired(): boolean {
      return this.refreshPayload && this.refreshPayload.exp
        ? Date.now() / 1000 > this.refreshPayload.exp
        : true;
    }
  },
  actions: {
    transfer(response: AxiosResponse<ApiResponse<TokenResponse>>) {
      if (response.status === 200 && response.data.code === 0) {
        if (response.data.result) {
          const { token, refresh } = response.data.result;
          this.token = token;
          this.refresh = refresh;
          this.payload = JSON.parse(atob(token.split('.')[1]));
          this.refreshPayload = JSON.parse(atob(refresh.split('.')[1]));
          storage.set(token, refresh);
          return;
        }
      }
      if (response.data) {
        throw response.data;
      }
      throw response;
    },
    async loadAuth(body: LoginBody): Promise<AxiosResponse<ApiResponse<TokenResponse>>> {
      const response = await login(body);
      this.transfer(response);
      return response;
    },
    async refreshAuth(): Promise<AxiosResponse<ApiResponse<TokenResponse>>> {
      const response = await refresh({ token: this.refresh } as RefreshBody);
      this.transfer(response);
      return response;
    },
    async logout() {
      window.sessionStorage.removeItem('token');
      window.sessionStorage.removeItem('refresh');
      this.$reset();
    }
  }
});
```
- index.d.ts
    - ไฟล์ที่ใช้ในการกำหนด type ของ store
```typescript
/**
 * token
 */
export interface TokenPayload {
  aud: number;
  sub: number;
  iss: string;
  iat: number;
  exp: number;
}

/**
 * Auth state
 */
export interface AuthState {
  /** token */
  token?: string;
  /** refresh token */
  refresh?: string;
  /** payload */
  payload?: TokenPayload;
  /** refresh payload */
  refreshPayload?: TokenPayload;
}

export interface ProfileMenus {
  id: number;
  name: string;
  type: MenuType;
  parentId?: number;
  privilege?: string;
  i18n?: string;
  url?: string;
  path?: string;
  meta?: string;
  sort: number;
  children?: ProfileMenus[];
}

export interface BankInfoApplyResponse {
  id: number;
  uid: number;
  type: number;
  bankName: string;
  cardNumber: string;
  cardName: string;
  amount: number;
  remark: string;
  state: number;
  cdt: number;
  udt: number;
}
```
- permission
    - ไฟล์ที่ใช้ในการกำหนด store ของ permission
```typescript
import type { MyRoleResponse } from '@/api';
import type { ProfileMenus } from '.';

import { defineStore } from 'pinia';

import { myRole } from '@/api/manager';

export const usePermissionStore = defineStore('permission', {
  state: () => ({
    menus: [] as MyRoleResponse[]
  }),
  getters: {
    isAuth(): (auth: string) => boolean {
      return (auth: string) => {
        return this.auths.includes(auth);
      };
    },
    auths(state): string[] {
      const auths = [] as string[];
      for (const menu of state.menus) {
        if (menu.privilege) {
          for (const auth of menu.privilege.split(',')) {
            auths.push(auth);
          }
        }
      }
      return auths;
    },
    treeMenus(state): ProfileMenus[] {
      const values = state.menus.map((item) => ({ ...item } as ProfileMenus));
      const tree = {
        0: []
      } as Record<number, ProfileMenus[]>;
      for (const value of values) {
        if (value.path?.startsWith('/')) {
          value.path = value.path.slice(1);
        }
        if (value.parentId) {
          if (!tree[value.parentId]) {
            tree[value.parentId] = [];
          }
          tree[value.parentId].push(value);
        } else {
          tree[0].push(value);
        }
      }
      for (const value of values) {
        if (tree[value.id]) {
          value.children = tree[value.id];
        }
      }
      return tree[0];
    }
  },
  actions: {
    async load() {
      const response = await myRole();
      if (response.status === 200 && response.data.code === 0) {
        this.menus = response.data.result || [];
      }
    }
  }
});
```
### View
> โฟลเดอร์ที่เก็บไฟล์ตั้งค่า view
- Home.vue
    - ไฟล์ที่ใช้ในการกำหนดหน้า Home
```vue
<template>
  <a-form layout="inline" :model="form">
    <a-form-item :label="$t('label.uid')" name="uid">
      <a-input v-model:value="form.uid"></a-input>
    </a-form-item>
    <a-button type="primary" :loading="state.searching" @click="handleChangeForm(true)">{{
      $t('action.search')
      }}</a-button>
  </a-form>
  <a-divider></a-divider>
  <a-table
      rowKey="id"
      :loading="state.searching"
      :dataSource="values"
      :columns="columns"
      :pagination="pagination"
      @change="handleFormChange"
  >
    <template #bodyCell="{ column, record }">
      <template v-if="column.dataIndex === 'inTime'">{{ formatDate(record.inTime) }}</template>
    </template>
  </a-table>
</template>

<script lang="ts">
  import { CipsConstant } from '@/api/enum';
  import { managerLoginLog } from '@/api/manager';
  import dayjs from 'dayjs';
  import type { LocationQuery } from 'vue-router';

  export default {
    data() {
      return {
        state: {
          searching: false as boolean,
          stateUid: undefined as number | undefined,
          activeUid: undefined as number | undefined
        },
        form: {
          id: undefined as number | undefined,
          uid: undefined as string | undefined,
          in_time: undefined as number | undefined,
          ipv4: undefined as string | undefined,
          ua: undefined as string | undefined
        },
        values: [],
        pagination: {
          total: 0 as number,
          current: CipsConstant.DEFAULT_PAGE,
          pageSize: CipsConstant.DEFAULT_LIMIT,
          showTotal: (total: number) => {
            return this.$t('message.pagination.total', [total]);
          },
          showSizeChanger: true
        }
      };
    },
    mounted() {
      this.loadForm(this.$route.query);
    },
    computed: {
      columns() {
        return [
          {
            title: 'id',
            dataIndex: 'id'
          },
          {
            title: this.$t('label.uid'),
            dataIndex: 'uid'
          },
          {
            title: this.$t('in_time'),
            dataIndex: 'inTime'
          },
          {
            title: this.$t('ipv4'),
            dataIndex: 'ipv4'
          },
          {
            title: this.$t('ua'),
            dataIndex: 'ua'
          }
        ];
      }
    },
    beforeRouteUpdate(to) {
      this.loadForm(to.query);
    },
    methods: {
      formatDate(unix: number) {
        return dayjs(unix).format('YYYY-MM-DD HH:mm');
      },
      handleChangeForm(force: boolean) {
        const query = {
          uid: this.form.uid,
          page: this.pagination.current,
          limit: this.pagination.pageSize
        };
        if (force === true) {
          query.t = Date.now();
          query.page = CipsConstant.DEFAULT_PAGE;
        }
        this.$router.push({
          query: query
        });
      },
      async loadForm(query: LocationQuery) {
        const { uid, page, limit } = query;
        this.form = { uid };
        this.pagination.current = Number(page || CipsConstant.DEFAULT_PAGE);
        this.pagination.pageSize = Number(limit || CipsConstant.DEFAULT_LIMIT);
        await this.loadData();
      },
      handleFormChange(pagination) {
        this.pagination = pagination;
        this.handleChangeForm(false);
      },
      async loadData() {
        this.state.searching = true;
        try {
          const response = await managerLoginLog({
            uid: this.form.uid,
            page: this.pagination.current,
            limit: this.pagination.pageSize
          });
          if (response.status === 200 && response.data.code === 0) {
            this.values = response.data.result;
            this.pagination.total = response.data.records;
          }
        } catch (error) {
          console.error(error);
        }
        this.state.searching = false;
      }
    }
  };
</script>

```
### Main
> ไฟล์ที่ใช้ในการกำหนดค่า Mina
```typescript
import { createApp } from 'vue';

import App from './App.vue';
import router from '@/router';
import store from '@/stores/pinia';
import i18n from '@/i18n';

import Antd from 'ant-design-vue';
import component from '@/component';

import 'ant-design-vue/dist/antd.css';
import '@/assets/main.scss';

const app = createApp(App);

app.use(store);
app.use(router);
app.use(i18n);
app.use(Antd);
app.use(component);

app.mount('#app');
```
### App
> ไฟล์ที่ใช้ในการกำหนดค่า App
```vue
<template>
  <a-config-provider :locale="locale">
    <router-view />
  </a-config-provider>
</template>
<script lang="ts">
import dayjs from 'dayjs';

import zhCN from 'ant-design-vue/es/locale/zh_CN';
import enUS from 'ant-design-vue/es/locale/en_US';
import localizedFormat from 'dayjs/plugin/localizedFormat';
import 'dayjs/locale/zh-cn';
import 'dayjs/locale/en';

export default {
  data() {
    return {
      locale: undefined
    };
  },
  computed: {
    locales() {
      return {
        zhCN,
        enUS
      } as Record<string, any>;
    }
  },
  watch: {
    '$i18n.locale': function () {
      this.changeLocale();
    }
  },
  mounted() {
    dayjs.extend(localizedFormat);
    this.changeLocale();
  },
  methods: {
    changeLocale() {
      this.locale = this.locales[this.$t('locale')];
      dayjs.locale(this.$t('code'));
    }
  }
};
</script>
```
### Vite
- vite.config.ts
    - ไฟล์ที่ใช้ในการกำหนดค่า Vite
```typescript
```typescript
import { fileURLToPath, URL } from 'node:url'

import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'

import Components from "unplugin-vue-components/vite";
import { AntDesignVueResolver } from "unplugin-vue-components/resolvers";

// https://vitejs.dev/config/
export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd());
  return {
    base: env.VITE_BASE_URL || '/',
    plugins: [
      vue(),
      Components({
        resolvers: [AntDesignVueResolver()],
      })
    ],
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
      }
    }
  }
});
```
## Script
### Start project
- คำสั่งในการติดตั้ง package
```shell
npm install
```
### How to run
- คำสั่งในการรันโปรเจค
```shell
npm run dev
```
### How to build
- คำสั่งในการ build โปรเจค
```shell
npm run build
```
### How to build test
- คำสั่งในการ build test โปรเจค
```shell
npm run build:test
```
###  How to build production
- คำสั่งในการ build production โปรเจค
```shell
npm run build:prod
```
### How to type check
- คำสั่งในการ type check โปรเจค
```shell
npm run type-check
```
### How to lint use EsLint
- คำสั่งในการ lint โปรเจค
```shell
npm run lint
```
### How to format code
- คำสั่งในการ format code โปรเจค
```shell
npm run format
```
### How to lint type check format code
- คำสั่งในการ lint type check format code โปรเจค
```shell
npm run before-vcs
```




  
  



