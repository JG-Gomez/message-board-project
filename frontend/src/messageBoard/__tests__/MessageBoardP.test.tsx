import {describe, expect, it} from "vitest";
import {render, screen} from "@testing-library/react";
import MessageBoardP from '../MessageBoardP.tsx'


describe('Message Board P', () => {
    it('should on initial render display a header', () => {

        render(<MessageBoardP/>)

        expect(screen.getByRole('heading')).toBeVisible();
    });

    it('should on initial render display a card with text input allowing users to type', () => {

        render(<MessageBoardP/>)

        expect(screen.getByRole('card')).toBeVisible();
    });
});