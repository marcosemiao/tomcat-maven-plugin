/*
 * Copyright 2015 Marco Semiao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package fr.ms.tomcat.manager;

/**
 *
 * @see <a href="http://marcosemiao4j.wordpress.com">Marco4J</a>
 *
 *
 * @author Marco Semiao
 *
 */
public class TomcatManagerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TomcatManagerException() {
    }

    public TomcatManagerException(final String message) {
	super(message);
    }

    public TomcatManagerException(final Throwable cause) {
	super(cause);
    }

    public TomcatManagerException(final String message, final Throwable cause) {
	super(message, cause);
    }
}