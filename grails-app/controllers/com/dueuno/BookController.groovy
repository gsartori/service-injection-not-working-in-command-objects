package com.dueuno

import dueuno.elements.contents.ContentCreate
import dueuno.elements.contents.ContentEdit
import dueuno.elements.contents.ContentList
import dueuno.elements.controls.TextField
import dueuno.elements.core.ElementsController
import grails.validation.Validateable

class BookController implements ElementsController {

    BookService bookService

    def index() {
        def c = createContent(ContentList)

        c.table.with {
            columns = [
                    'title',
                    'author',
            ]
            body = bookService.list()
        }

        display content: c
    }

    private buildForm(Map obj = null) {
        def c = obj
                ? createContent(ContentEdit)
                : createContent(ContentCreate)

        c.form.with {
            addField(
                    class: TextField,
                    id: 'title',
            )
            addField(
                    class: TextField,
                    id: 'author',
            )
        }

        if (obj) {
            c.form.values = obj
        }

        return c
    }

    def create() {
        def c = buildForm()
        display content: c, modal: true
    }

    def onCreate(BookValidator obj) {
        if (obj.hasErrors()) {
            display errors: obj
            return
        }

        bookService.create(params)
        display action: 'index'
    }

    def edit() {
        def book = bookService.get(params.id)
        def c = buildForm(book)
        display content: c, modal: true
    }

    def onEdit(BookValidator obj) {
        if (obj.hasErrors()) {
            display errors: obj
            return
        }

        bookService.update(params)
        display action: 'index'
    }

    def onDelete() {
        try {
            bookService.delete(params.id)
            display action: 'index'

        } catch (Exception e) {
            display exception: e
        }
    }
}

class BookValidator implements Validateable {

    def bookService

    String title
    String author

    static constraints = {
        title validator: { val, obj, errors ->
            if (bookService.getByTitle(val)) {
                errors.rejectValue('title', 'unique')
            }
        }
    }
}