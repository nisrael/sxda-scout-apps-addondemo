import {ObjectFactory} from '@eclipse-scout/core';

import * as self from './index';

import '@sxda/scout-addon-ace';
import 'ace-code/esm-resolver';

export default self;
ObjectFactory.get().registerNamespace('addondemo', self);


