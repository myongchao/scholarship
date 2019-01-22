export const form = {
  props: {
    eid: {
      type: Number
    },
    isEdit: {
      type: Boolean,
      default: false
    },
    line: {
      type: Boolean,
      default: false
    },
    initEntity: {}
  },
  data() {
    return {
      entity: {

      }
    }
  },
  created() {
    this.init()
    this.load()
  },
  computed: {

  },
  watch: {
    eid() {
      this.load()
    },
    initEntity(n, o) {
      this.entity = n
    }
  },
  methods: {
    reset() {
      this.$refs.form.resetFields()
    },
    validate(e) {
      this.$refs.form.validate(e)
    },
    getValue() {
      return this.entity
    },
    init() {
      if (this.initEntity) {
        this.entity = this.initEntity
      }
    }
  }
}
export const uploadForm = {
  mixins: [form],
  computed: {
    action() {
      return process.env.BASE_API + '/AppFile/file/upload2'
    },
    host() {
      return process.env.BASE_API
    }
  }
}
