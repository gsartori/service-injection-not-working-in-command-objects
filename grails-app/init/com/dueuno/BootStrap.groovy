package com.dueuno

import dueuno.elements.core.ApplicationService

class BootStrap {

    ApplicationService applicationService

    def init = { servletContext ->
        applicationService.init {

            registerFeature(
                    controller: 'book',
                    icon: 'fa-book',
            )
            registerFeature(
                    controller: 'read',
                    icon: 'fa-glasses',
            )
        }
    }

    def destroy = {
    }
}