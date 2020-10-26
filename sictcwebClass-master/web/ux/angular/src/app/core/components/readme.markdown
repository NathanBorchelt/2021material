### Core Components Readme
Devon Germano - devon@cphandheld.com

#### What components should I put here?

1. Components that are exported from a feature module, located in the same directory.
2. Components that do not import any other module, other than `CoreModule`, a module exported from this directory, or a module imported via NPM.
3. Modules that can be copy and pasted into a new project, without copy any additional dependencies, other than another module from this directory or an NPM package.
4. NEVER a component that's contains a routing module. (No "page" components)

#### TLDR:
Place components that are exported from their own module here. Don't import any modules into that module, other than other modules contained in THIS directory or a module from a library you've added to `node_modules`. If it dosen't fit that description, it should probably go in `shared-components`.
