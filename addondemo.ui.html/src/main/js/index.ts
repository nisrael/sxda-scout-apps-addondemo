/*
 * Copyright (c) 2010-20250906-180705 BSI Business Systems Integration AG
 * Copyright (c) 2023-20250906-180705 Nils Israel
 *
 * This program is an extension of the original work from the Eclipse Scout Project,
 * available at https://www.eclipse.org/scout/.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
import {ObjectFactory} from '@eclipse-scout/core';

import * as self from './index';

import '@sxda/scout-addon-ace';
import 'ace-code/esm-resolver';

export default self;
ObjectFactory.get().registerNamespace('addondemo', self);


