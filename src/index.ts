import { registerPlugin } from '@capacitor/core';

import type { CapacitorAxaMobileSdkPlugin } from './definitions';

const CapacitorAxaMobileSdk = registerPlugin<CapacitorAxaMobileSdkPlugin>(
  'CapacitorAxaMobileSdk',
  {
    web: () => import('./web').then(m => new m.CapacitorAxaMobileSdkWeb()),
  },
);

export * from './definitions';
export { CapacitorAxaMobileSdk };
