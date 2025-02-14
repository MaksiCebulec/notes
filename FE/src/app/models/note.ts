export interface Note {
    id: number,
    title: string,
    description: string,
    date: string, //in the backend is LocalDate, but here is string because it's easier to work with and then I change it in the backend (in db is saved as LocalDate)
    tag: string,
    favourite: boolean
}