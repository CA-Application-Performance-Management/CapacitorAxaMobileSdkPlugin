// @ts-nocheck

import { WebPlugin } from '@capacitor/core';

import type { CapacitorAxaMobileSdkPlugin } from './definitions';

export class CapacitorAxaMobileSdkWeb
  extends WebPlugin
  implements CapacitorAxaMobileSdkPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
