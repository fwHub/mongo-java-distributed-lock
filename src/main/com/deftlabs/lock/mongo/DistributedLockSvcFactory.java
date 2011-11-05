/**
 * Copyright 2011, Deft Labs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.deftlabs.lock.mongo;

// Lib
import com.deftlabs.lock.mongo.impl.SvcImpl;

/**
 * The distrbuted lock service factory.
 */
public final class DistributedLockSvcFactory {

    public DistributedLockSvcFactory(final DistributedLockSvcOptions pOptions) {
        _options = pOptions;
    }

    public DistributedLockSvc getLockSvc() {
        if (_lockSvc != null) return _lockSvc;

        synchronized(_mutex) {
            if (_lockSvc != null) return _lockSvc;

            final SvcImpl svc = new SvcImpl(_options);
            svc.init();
            _lockSvc = svc;
            return _lockSvc;
        }
    }

    private static DistributedLockSvc _lockSvc = null;

    private final DistributedLockSvcOptions _options;
    private final Object _mutex = new Object();
}

