import defaultSettings from '@/settings'

const title = defaultSettings.title || 'Little Bee DevOps'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
