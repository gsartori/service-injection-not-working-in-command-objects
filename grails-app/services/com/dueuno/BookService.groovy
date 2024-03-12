package com.dueuno

class BookService {

    private static final data = [
            [id: '1', title: 'The Teachings of Don Juan', author: 'Carlos Castaneda', description: 'This is a nice fictional book'],
            [id: '2', title: 'The Antipodes of the Mind', author: 'Benny Shanon', description: 'This is a nice scientific book'],
    ]

    List<Map> list() {
        return data
    }

    Map get(Serializable id) {
        return data.find { it.id == id }
    }

    Map getByTitle(String title) {
        return data.find { it.title == title }
    }

    void create(Map record) {
        record.id = data.size() + 1
        data.add(record)
    }

    void update(Map record) {
        if (!record.id) throw new Exception("'id' required to update a record!")
        Map item = data.find { it.id == record.id }
        if (item) {
            item.title == record.title
            item.author = record.author
        }
    }

    void delete(Serializable id) {
        data.removeAll { it.id == id }
    }
}
