import {apiSlice} from "../../app/apiSlice";

export const mainApiSlice = apiSlice.injectEndpoints({
    endpoints: builder => ({
        getResults: builder.query({
            query: () => ({
                url: '/results',
                method: 'GET'
            })
        }),
        checkCoordinates: builder.mutation({
            query: coordinates => ({
                url: '/results',
                method: 'POST',
                body: { ...coordinates }
            })
        }),
        clearResults: builder.mutation({
            query: () => ({
                url: '/results',
                method: 'DELETE',
            }),
        }),
    })
})

export const {
    useGetResultsQuery,
    useCheckCoordinatesMutation,
    useClearResultsMutation
} = mainApiSlice