package com.dueuno


import dueuno.elements.contents.ContentForm
import dueuno.elements.controls.Select
import dueuno.elements.controls.Textarea
import dueuno.elements.core.ElementsController

class ReadController implements ElementsController {

    BookService bookService

    def index() {
        def c = createContent(ContentForm)

        c.header.removeNextButton()

        c.form.with {
            addField(
                    class: Select,
                    optionsFromRecordset: bookService.list(),
                    onChange: 'onBookChange',
                    id: 'book',
            )
            addField(
                    class: Textarea,
                    id: 'description',
            )
        }

        display content: c
    }

    def onBookChange() {
        def t = createTransition()
        def book = bookService.get(params.book)
        if (book) {
            t.set('description', 'readonly', true)
            t.set('description', book.description)
        } else {
            t.set('description', 'readonly', false)
            t.set('description', null)
        }
        display transition: t
    }
}
