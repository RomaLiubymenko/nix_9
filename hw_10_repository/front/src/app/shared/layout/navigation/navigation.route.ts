import { Route } from '@angular/router';
import {NavigationComponent} from "./navigation.component";


export const navigationRoute: Route = {
  path: '',
  data: {
    breadcrumb: ''
  },
  component: NavigationComponent,
  outlet: 'navigation'
};
