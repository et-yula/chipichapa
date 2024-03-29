import { apiSlice } from "../../app/apiSlice";

export const authApiSlice = apiSlice.injectEndpoints({
    endpoints: builder => ({
        login: builder.mutation({
            query: credentials => ({
                url: '/auth/login',
                method: 'POST',
                body: { ...credentials }
            }),
        }),
        registration: builder.mutation({
            query: credentials => ({
                url: '/auth/registration',
                method: 'POST',
                body: { ...credentials }
            }),
        }),
    })
});

export const {
    useLoginMutation,
    useRegistrationMutation
} = authApiSlice;