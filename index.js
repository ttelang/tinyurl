// server.js
const express = require('express')
const linkRoute = require('./route/link')
const redirectRoute = require('./route/redirect')

const app = express()
app.use(express.json())
app.use(express.urlencoded({ extended: true }));

app.use('/api/link', linkRoute)
app.use('/', redirectRoute)

app.listen(4445, () => {
    console.log('Server started on http://urlshrtnr.taruntelang.repl.co')
});